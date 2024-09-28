import toast from "react-hot-toast";
import axiosInstance from "../configs/axios";
import type { ErrorResponse } from "../models/ErrorsResponse";

type LoginPayLoad = {
  email: string;
  password: string;
};
type LoginResponse = {
  code: number;
  message: string;
};
const login = (login: LoginPayLoad): any => {
  // console.log(handlerApi(login));
  return handlerApi(login);
};

const handlerApi = async (data: LoginPayLoad): Promise<boolean> => {
  try {
    const result = await axiosInstance.post(`/auth/login`, data);
    console.log(result.data);
    if (result.status === 200) {
      console.log("200 code");
      if (!localStorage.getItem("_tokenLogin")) {
        localStorage.setItem("_tokenLogin", result.data.message);
      }
      return true;
    }
    return false;
  } catch (error: any) {
    // console.error("Error during login:", error.response.data);

    const response: ErrorResponse = error.response.data;
    if (response.code === 404) {
      toast.error(response.message);
    }

    return false;
  }
};

export { login };
