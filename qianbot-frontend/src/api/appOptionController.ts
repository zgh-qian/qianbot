// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addAppOption POST /api/app/option/add */
export async function addAppOptionUsingPost(
  body: API.AppoptionAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseLong_>('/api/app/option/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteAppOption POST /api/app/option/delete */
export async function deleteAppOptionUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/option/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAppOption POST /api/app/option/get */
export async function getAppOptionUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppOptionUsingPOSTParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppoption_>('/api/app/option/get', {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateAppOption POST /api/app/option/update */
export async function updateAppOptionUsingPost(
  body: API.AppoptionUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/option/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
