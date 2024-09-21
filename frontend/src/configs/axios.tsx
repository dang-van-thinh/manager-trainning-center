import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api/",
  timeout: 10000,
  headers: {
    "X-Custom-Header": "foobar",
    "Content-Type": "application/json",
  },
});

axiosInstance.interceptors.response.use(function (response): any {
  return response.data;
});

export default axiosInstance;
