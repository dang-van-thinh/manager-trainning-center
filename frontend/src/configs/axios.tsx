import axios from "axios";
// const token = localStorage.getItem("_tokenLogin");
// console.log("token trong axios " + token);
const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api/",
  // timeout: 10000,
  // headers: {
  //   "Content-Type": "application/json",
  //   Authorization: token ? `Bearer ${token}` : "",
  // },
  withCredentials: true, // bat de gui nhan token
});

// axiosInstance.defaults.headers.common["Authorization"] = token;
// cấu hình dữ liệu trả ra mỗi data thôi
// axiosInstance.interceptors.response.use(function (response): any {
//   return response.data;
// });

export default axiosInstance;
