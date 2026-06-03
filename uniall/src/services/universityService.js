import axios from 'axios';

const API_BASE_URL = 'http://localhost:8083/api/v1';

const api = axios.create({
  baseURL: API_BASE_URL,
});

export const getAllUniversities = async () => {
  const response = await api.get('/universities');
  return response.data;
};

export const searchUniversity = async (element, elementValue) => {
  try {
    const response = await api.get(`/universities/search`, {
      params: {
        element,
        elementValue
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error searching universities:', error);
    throw error;
  }
};

export const getCountries = async () => {
  try {
    const response = await api.get('/countries');
    return response.data;
  } catch (error) {
    console.error('Error fetching countries:', error);
    throw error;
  }
};


