import axiosInstance from "../configs/axios";

type LoginPayLoad = {
  email: string;
  password: string;
};

const login = (login: LoginPayLoad) => {
  // console.log(handlerApi(login));
  return handlerApi(login);
};

const handlerApi = async (data: LoginPayLoad): Promise<boolean> => {
  try {
    const result = await axiosInstance.get(
      `/auth/test?email=${data?.email}&password=${data?.password}`
    );

    console.log(result);

    return true;
  } catch (error) {
    return false;
  }
};

export { login };
