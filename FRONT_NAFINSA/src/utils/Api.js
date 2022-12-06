

import axios from 'axios';

const { API } = process.env;

export default axios.create({
  baseURL: `${API}`
});