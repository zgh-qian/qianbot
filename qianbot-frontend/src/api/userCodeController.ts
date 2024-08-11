// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addUserCode POST /api/user/code/add */
export async function addUserCodeUsingPost(
  body: API.UserCodeAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseLong_>('/api/user/code/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteUserCode POST /api/user/code/delete */
export async function deleteUserCodeUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/user/code/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getUserCodeVOById GET /api/user/code/get */
export async function getUserCodeVoByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserCodeVOByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserCodeVO_>('/api/user/code/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getUserCodeVOList POST /api/user/code/get/list */
export async function getUserCodeVoListUsingPost(
  body: API.UserCodeQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageUserCodeVO_>('/api/user/code/get/list', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getUserCodeSelfVOById GET /api/user/code/get/self */
export async function getUserCodeSelfVoByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserCodeSelfVOByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseUserCodeSelfVO_>('/api/user/code/get/self', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getUserCodeSelfVOList POST /api/user/code/get/self/list */
export async function getUserCodeSelfVoListUsingPost(
  body: API.UserCodeQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageUserCodeSelfVO_>('/api/user/code/get/self/list', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** updateUserCode POST /api/user/code/update */
export async function updateUserCodeUsingPost(
  body: API.UserCodeUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/user/code/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
