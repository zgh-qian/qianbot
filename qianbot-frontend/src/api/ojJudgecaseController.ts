// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addOjJudgecase POST /api/oj/judgecase/add */
export async function addOjJudgecaseUsingPost(
  body: API.OjJudgecaseAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/oj/judgecase/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteOjJudgecase POST /api/oj/judgecase/delete */
export async function deleteOjJudgecaseUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/oj/judgecase/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getOjJudgecaseById GET /api/oj/judgecase/get */
export async function getOjJudgecaseByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getOjJudgecaseByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseOjJudgecaseVO_>('/api/oj/judgecase/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getOjJudgecaseList POST /api/oj/judgecase/list */
export async function getOjJudgecaseListUsingPost(
  body: API.OjJudgecaseQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageOjJudgecaseVO_>('/api/oj/judgecase/list', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** updateOjJudgecase POST /api/oj/judgecase/update */
export async function updateOjJudgecaseUsingPost(
  body: API.OjJudgecaseUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/oj/judgecase/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
