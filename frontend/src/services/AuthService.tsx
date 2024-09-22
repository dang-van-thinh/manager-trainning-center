import toast from "react-hot-toast";
import axiosInstance from "../configs/axios";
import type { ErrorResponse } from "../types/ErrorsResponse";

type LoginPayLoad = {
  email: string;
  password: string;
};

const login = (login: LoginPayLoad): any => {
  // console.log(handlerApi(login));
  return handlerApi(login);
};

const handlerApi = async (data: LoginPayLoad): Promise<boolean> => {
  try {
    const result = await axiosInstance.post(`/auth/login`, data);

    console.log(result);

    if (result.status === 200) {
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
