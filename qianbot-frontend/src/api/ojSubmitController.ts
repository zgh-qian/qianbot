// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addOjSubmit POST /api/oj/submit/add */
export async function addOjSubmitUsingPost(
  body: API.OjSubmitAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseLong_>('/api/oj/submit/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteOjSubmit POST /api/oj/submit/delete */
export async function deleteOjSubmitUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/oj/submit/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getOjSubmitVO GET /api/oj/submit/get */
export async function getOjSubmitVoUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getOjSubmitVOUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseOjSubmitVO_>('/api/oj/submit/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getOjSubmitVOList POST /api/oj/submit/list */
export async function getOjSubmitVoListUsingPost(
  body: API.OjSubmitQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageOjSubmitVO_>('/api/oj/submit/list', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
