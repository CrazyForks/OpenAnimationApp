// Function to dynamically load scripts
function loadScript(src, onProgress, expectedSize) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', src, true);

        xhr.onprogress = (event) => {
            if (expectedSize && expectedSize > 0 && event.loaded) {
                const percentComplete = (event.loaded / expectedSize) * 100;
                if (onProgress) onProgress(Math.min(Math.round(percentComplete), 100));
            } else if (event.lengthComputable && event.total > 0) {
                const percentComplete = (event.loaded / event.total) * 100;
                if (onProgress) onProgress(Math.round(percentComplete));
            } else {
                if (onProgress) onProgress(null);
            }
        };

        xhr.onload = () => {
            if (xhr.status >= 200 && xhr.status < 300) {
                const script = document.createElement('script');
                script.type = 'application/javascript';
                script.textContent = xhr.responseText;
                document.body.appendChild(script);
                if (onProgress) onProgress(100); // Ensure 100% is reported
                resolve();
            } else {
                reject(new Error(`Failed to load script: ${src}. Status: ${xhr.status}`));
            }
        };

        xhr.onerror = () => {
            reject(new Error(`Failed to load script: ${src}. Network error.`));
        };

        xhr.send();
    });
}

function getFileSizeOrDefault(url, defaultSize) {
    return new Promise((resolve) => {
        const xhr = new XMLHttpRequest();
        xhr.open('HEAD', url, true);
        xhr.onload = () => {
            if (xhr.status >= 200 && xhr.status < 300) {
                const contentLength = xhr.getResponseHeader('Content-Length');
                if (contentLength) {
                    resolve(parseInt(contentLength, 10));
                } else {
                    console.warn(`Content-Length header not found for ${url}, using default size.`);
                    resolve(defaultSize);
                }
            } else {
                console.warn(`HEAD request failed for ${url} (status: ${xhr.status}), using default size.`);
                resolve(defaultSize);
            }
        };
        xhr.onerror = () => {
            console.warn(`Network error during HEAD request for ${url}, using default size.`);
            resolve(defaultSize);
        };
        xhr.send();
    });
}

async function loadScriptAndUpdateOverallProgress(
    scriptSrc,
    scriptSize,
    totalBytesAlreadyLoaded,
    totalBytesToLoadOverall,
    loadingMessage,
    updateStatusFn
) {
    return loadScript(scriptSrc, (individualProgressPercentage) => {
        let currentFileBytesLoaded = 0;
        if (individualProgressPercentage !== null && scriptSize > 0) {
            currentFileBytesLoaded = (individualProgressPercentage / 100) * scriptSize;
        }

        let overallProgressPercentage = 0;
        if (totalBytesToLoadOverall > 0) {
            overallProgressPercentage = ((totalBytesAlreadyLoaded + currentFileBytesLoaded) / totalBytesToLoadOverall) * 100;
        } else if (individualProgressPercentage !== null) {
            overallProgressPercentage = individualProgressPercentage;
        }
        updateStatusFn(loadingMessage, Math.min(Math.round(overallProgressPercentage), 100));
    }, scriptSize);
}


// Update loading status and progress
function updateLoadingStatus(message, progress = null) {
    const loadingText = document.getElementById('loading-text');
    if (loadingText) {
        loadingText.textContent = message;
    }

    const progressElement = document.getElementById('loading-progress');
    if (progressElement && progress !== null) {
        progressElement.textContent = `${progress}%`;
    }

    const progressBar = document.getElementById('progress-bar');
    if (progressBar && progress !== null) {
        progressBar.style.width = `${progress}%`;
    }
}

// Initialize loading
document.addEventListener('DOMContentLoaded', async () => {
    try {
        // Create loading indicator
        const loadingContainer = document.createElement('div');
        loadingContainer.className = 'loading-container';

        const spinner = document.createElement('div');
        spinner.className = 'loading-spinner';

        const loadingText = document.createElement('div');
        loadingText.id = 'loading-text';
        loadingText.className = 'loading-text';
        loadingText.textContent = 'Loading resources...';

        const progressElement = document.createElement('div');
        progressElement.id = 'loading-progress';
        progressElement.className = 'loading-progress';
        progressElement.textContent = '0%';

        loadingContainer.appendChild(spinner);
        loadingContainer.appendChild(loadingText);
        loadingContainer.appendChild(progressElement);

        // Add to splash screen
        const splash = document.getElementById('splash');
        splash.appendChild(loadingContainer);

        const FALLBACK_SKIKO_JS_SIZE_BYTES = 1024 * 1024; // 1MB
        const FALLBACK_OPEN_ANIMATION_JS_SIZE_BYTES = 5 * 1024 * 1024; // 5MB

        updateLoadingStatus('Determining file sizes...', 0);

        const skikoJsSize = await getFileSizeOrDefault('skiko.js', FALLBACK_SKIKO_JS_SIZE_BYTES);
        const openAnimationJsSize = await getFileSizeOrDefault('openAnimation.js', FALLBACK_OPEN_ANIMATION_JS_SIZE_BYTES);

        const totalBytesToLoad = skikoJsSize + openAnimationJsSize;

        if (totalBytesToLoad === 0) {
            console.warn('Total file size is zero. Progress reporting will be inaccurate or step-based.');
        }

        let totalBytesLoadedSoFar = 0;

        // Load skiko.js
        const skikoLoadingMsg = 'Loading Skiko framework...';
        updateLoadingStatus(skikoLoadingMsg, 0);
        await loadScriptAndUpdateOverallProgress(
            'skiko.js',
            skikoJsSize,
            totalBytesLoadedSoFar,
            totalBytesToLoad,
            skikoLoadingMsg,
            updateLoadingStatus
        );
        totalBytesLoadedSoFar += skikoJsSize;
        // Ensure this step's progress is fully accounted for in the overall calculation for the next step's base
        let currentOverallProgress = totalBytesToLoad > 0 ? (totalBytesLoadedSoFar / totalBytesToLoad) * 100 : 50;
        updateLoadingStatus('Skiko framework loaded.', Math.round(currentOverallProgress));

        // Load application script
        const appLoadingMsg = 'Loading application...';
        updateLoadingStatus(appLoadingMsg, Math.round(currentOverallProgress));
        await loadScriptAndUpdateOverallProgress(
            'openAnimation.js',
            openAnimationJsSize,
            totalBytesLoadedSoFar,
            totalBytesToLoad,
            appLoadingMsg,
            updateLoadingStatus
        );
        totalBytesLoadedSoFar += openAnimationJsSize;
        currentOverallProgress = totalBytesToLoad > 0 ? (totalBytesLoadedSoFar / totalBytesToLoad) * 100 : 100;

        updateLoadingStatus('Application loaded.', Math.round(currentOverallProgress));
        updateLoadingStatus('Starting application...', 100);

    } catch (error) {
        console.error('Failed to load application:', error);
        updateLoadingStatus('Failed to load application. Please try refreshing the page.', null); // No progress % on error
    }
});