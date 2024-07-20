// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addAppAnswer POST /api/app/answer/add */
export async function addAppAnswerUsingPost(
  body: API.AppanswerAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseLong_>('/api/app/answer/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteAppAnswer POST /api/app/answer/delete */
export async function deleteAppAnswerUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/answer/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** generateId GET /api/app/answer/gene/id */
export async function generateIdUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseLong_>('/api/app/answer/gene/id', {
    method: 'GET',
    ...(options || {}),
  });
}

/** getAppAnswerPageVO POST /api/app/answer/get/page/vo */
export async function getAppAnswerPageVoUsingPost(
  body: API.AppanswerQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageAppanswerVO_>('/api/app/answer/get/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAppAnswerVOById GET /api/app/answer/get/vo */
export async function getAppAnswerVoByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppAnswerVOByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppanswerVO_>('/api/app/answer/get/vo', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getAppAnswerCount GET /api/app/answer/stats/answer/count */
export async function getAppAnswerCountUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListAppAnswerCountVO_>('/api/app/answer/stats/answer/count', {
    method: 'GET',
    ...(options || {}),
  });
}

/** getAppResultCount GET /api/app/answer/stats/result/count */
export async function getAppResultCountUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppResultCountUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListAppAnswerResultNameCountVO_>(
    '/api/app/answer/stats/result/count',
    {
      method: 'GET',
      params: {
        ...params,
      },
      ...(options || {}),
    },
  );
}
