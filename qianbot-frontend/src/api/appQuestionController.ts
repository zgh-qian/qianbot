// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addAppQuestion POST /api/app/question/add */
export async function addAppQuestionUsingPost(
  body: API.AppquestionAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseLong_>('/api/app/question/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteAppQuestion POST /api/app/question/delete */
export async function deleteAppQuestionUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/question/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAppQuestionById GET /api/app/question/get */
export async function getAppQuestionByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppQuestionByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppquestion_>('/api/app/question/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** updateAppQuestion POST /api/app/question/update */
export async function updateAppQuestionUsingPost(
  body: API.AppquestionUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/question/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
