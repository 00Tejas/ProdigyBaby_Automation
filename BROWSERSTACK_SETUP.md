# BrowserStack Setup Guide

## Quick Start

### 1. Upload Your App to BrowserStack
- Go to BrowserStack Dashboard → App Automate → Upload App
- Upload your APK file
- Copy the app ID (format: `bs://...`)

### 2. Update browserstack.yml
Replace the placeholder in `browserstack.yml`:
```yaml
app: "bs://your-actual-app-id-here"
```

### 3. Run Tests on BrowserStack

**Option A: Using System Property (Recommended for testing)**
```bash
mvn test -DrunMode=browserstack
```

**Option B: Update config.properties**
Change `runMode=local` to `runMode=browserstack` in `src/main/resources/config.properties`

### 4. Run Specific Test
```bash
mvn test -DrunMode=browserstack -Dtest=BrowserStackSimpleTest
```

## Configuration Files

- **browserstack.yml** - BrowserStack credentials and configuration
- **config.properties** - Run mode setting (local/browserstack)

## Current Configuration

- **Device**: Google Pixel 7, Android 14.0
- **Appium Version**: 2.0
- **Test Observability**: Enabled
- **Project**: MobileApp_Automation
- **Build**: Regression_Build_01

## Troubleshooting

1. **App ID not set**: Make sure you've replaced `bs://<APP_ID_PLACEHOLDER>` with your actual app ID
2. **Connection failed**: Verify your BrowserStack credentials in `browserstack.yml`
3. **Test timeout**: Check your BrowserStack account has available parallel sessions

## Switch Back to Local

```bash
mvn test -DrunMode=local
```
Or change `runMode=local` in `config.properties`

