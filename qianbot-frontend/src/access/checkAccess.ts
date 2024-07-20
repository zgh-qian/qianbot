import ACCESS_ENUM from "@/access/accessEnum";

/**
 * 检查权限
 * @param loginUser
 * @param needAccess
 */
const checkAccess = (
  loginUser: API.LoginUserVO,
  needAccess: string = ACCESS_ENUM.NOT_LOGIN
) => {
  const loginUserAccess = loginUser?.userRole ?? ACCESS_ENUM.NOT_LOGIN;
  if (needAccess === ACCESS_ENUM.NOT_LOGIN) {
    return true;
  } else if (needAccess === ACCESS_ENUM.USER) {
    return loginUserAccess !== ACCESS_ENUM.NOT_LOGIN;
  } else if (needAccess === ACCESS_ENUM.ADMIN) {
    return loginUserAccess === ACCESS_ENUM.ADMIN;
  }
  return true;
};
export default checkAccess;
