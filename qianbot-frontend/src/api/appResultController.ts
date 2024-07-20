// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addAppResult POST /api/app/result/add */
export async function addAppResultUsingPost(
  body: API.AppresultAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseLong_>('/api/app/result/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteAppResult POST /api/app/result/delete */
export async function deleteAppResultUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/result/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAppResultById GET /api/app/result/get */
export async function getAppResultByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppResultByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppresultVO_>('/api/app/result/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getAppResultByAppId GET /api/app/result/get/list */
export async function getAppResultByAppIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppResultByAppIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListAppresultVO_>('/api/app/result/get/list', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateAppResult POST /api/app/result/update */
export async function updateAppResultUsingPost(
  body: API.AppresultUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/result/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
