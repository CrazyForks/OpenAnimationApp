// Function to dynamically load scripts
function loadScript(src, onProgress) {
    return new Promise((resolve, reject) => {
        // For scripts we can't directly track download progress with vanilla JS
        // So we'll simulate progress for better UX
        const simulateProgress = () => {
            let progress = 0;
            const interval = setInterval(() => {
                progress += Math.random() * 5;
                if (progress > 95) {
                    progress = 95; // Cap at 95% until actual load completes
                    clearInterval(interval);
                }
                if (onProgress) onProgress(Math.min(Math.round(progress), 95));
            }, 200);
            return interval;
        };

        const progressInterval = simulateProgress();

        const script = document.createElement('script');
        script.src = src;
        script.type = 'application/javascript';
        script.onload = () => {
            clearInterval(progressInterval);
            if (onProgress) onProgress(100);
            setTimeout(resolve, 200); // Small delay to show 100%
        };
        script.onerror = () => {
            clearInterval(progressInterval);
            reject(new Error(`Failed to load script: ${src}`));
        };
        document.body.appendChild(script);
    });
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

        // Track overall progress
        const totalSteps = 2; // skiko.js and stayintap.js
        let completedSteps = 0;
        let currentProgress = 0;

        // Load skiko.js first
        updateLoadingStatus('Loading Skiko framework...', 0);
        await loadScript('skiko.js', (progress) => {
            currentProgress = (completedSteps * 100 + progress) / totalSteps;
            updateLoadingStatus('Loading Skiko framework...', Math.round(currentProgress));
        });

        completedSteps++;

        // Then load application script
        updateLoadingStatus('Loading application...', 50);
        await loadScript('openAnimation.js', (progress) => {
            currentProgress = (completedSteps * 100 + progress) / totalSteps;
            updateLoadingStatus('Loading application...', Math.round(currentProgress));
        });

        // App is ready when both scripts are loaded
        updateLoadingStatus('Starting application...', 100);

        // The app's initialization will hide the splash screen when ready
        // If your app needs more time to initialize, you might want to expose a function to call hideLoadingScreen()

    } catch (error) {
        console.error('Failed to load application:', error);
        updateLoadingStatus('Failed to load application. Please try refreshing the page.');
    }
});
