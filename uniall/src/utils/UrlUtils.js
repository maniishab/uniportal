// Util functions for URL manipulation
// Transform the URL to HTTPS

const convertToHttps = (url) => {
    if (url.startsWith('http://')) {
      return url.replace('http://', 'https://');
    }
    return url;
  };