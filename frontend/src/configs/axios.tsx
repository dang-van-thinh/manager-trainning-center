import axios from "axios";

const tokenLogin = localStorage.getItem("_tokenLogin");

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api/",
  timeout: 10000,
  headers: {
    "X-Custom-Header": "foobar",
    "Content-Type": "application/json",
    Authorization: `Bearer ${tokenLogin}`,
  },
  withCredentials: true,
});

// cấu hình dữ liệu trả ra mỗi data thôi
// axiosInstance.interceptors.response.use(function (response): any {
//   return response.data;
// });

export default axiosInstance;
