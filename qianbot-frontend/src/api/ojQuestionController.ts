// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addOjQuestionDetail POST /api/oj/question/add */
export async function addOjQuestionDetailUsingPost(
  body: API.OjQuestionDetailAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseLong_>('/api/oj/question/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteOjQuestionDetail POST /api/oj/question/delete */
export async function deleteOjQuestionDetailUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/oj/question/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getOjQuestionDetail GET /api/oj/question/get */
export async function getOjQuestionDetailUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getOjQuestionDetailUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseOjQuestionDetailVO_>('/api/oj/question/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getOjQuestionVOList POST /api/oj/question/list */
export async function getOjQuestionVoListUsingPost(
  body: API.OjQuestionQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageOjQuestionVO_>('/api/oj/question/list', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** updateOjQuestionDetail POST /api/oj/question/update */
export async function updateOjQuestionDetailUsingPost(
  body: API.OjQuestionDetailUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/oj/question/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
