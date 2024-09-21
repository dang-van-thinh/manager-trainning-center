type LoginPayLoad = {
  email: string;
  password: string;
};

const login = async (login: LoginPayLoad) => {
  // console.log(handlerApi(login));
  return await handlerApi(login);
};

const handlerApi = async (data: LoginPayLoad) => {
  const api = `http://localhost:8080/api/auth/test?email=${data.email}&password=${data.password}`;

  try {
    const response = await fetch(api, {
      method: "get",
      headers: {
        "content-type": "application/json;charset=UTF-8",
      },
    });
    const result = await response.json();
    console.log(result);
    return result; // Return the API response
  } catch (errors) {
    console.log(errors);
    throw errors; // Optional: rethrow error if needed
  }
};

export { login };
