// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addApp POST /api/app/add */
export async function addAppUsingPost(body: API.AppAddRequest, options?: { [key: string]: any }) {
  return request<API.BaseResponseLong_>('/api/app/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAIGenerateQuestionSSE GET /api/app/ai/generate/sse */
export async function getAiGenerateQuestionSseUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAIGenerateQuestionSSEUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.SseEmitter>('/api/app/ai/generate/sse', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getAIGenerateQuestionSync POST /api/app/ai/generate/sync */
export async function getAiGenerateQuestionSyncUsingPost(
  body: API.AppAIGenerateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListAppAIGenerateVO_>('/api/app/ai/generate/sync', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** doAIAppReview GET /api/app/ai/review/app */
export async function doAiAppReviewUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.doAIAppReviewUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/ai/review/app', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteApp POST /api/app/delete */
export async function deleteAppUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAppDetail GET /api/app/detail */
export async function getAppDetailUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppDetailUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppDetailVO_>('/api/app/detail', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** editApp POST /api/app/edit */
export async function editAppUsingPost(body: API.AppEditRequest, options?: { [key: string]: any }) {
  return request<API.BaseResponseBoolean_>('/api/app/edit', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAppVOById GET /api/app/get/vo */
export async function getAppVoByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppVOByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseAppVO_>('/api/app/get/vo', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** listAppByPage POST /api/app/list/page */
export async function listAppByPageUsingPost(
  body: API.AppQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageApp_>('/api/app/list/page', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** listAppVOByPage POST /api/app/list/page/vo */
export async function listAppVoByPageUsingPost(
  body: API.AppQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageAppVO_>('/api/app/list/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** listMyAppVOByPage POST /api/app/my/list/page/vo */
export async function listMyAppVoByPageUsingPost(
  body: API.AppQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageAppVO_>('/api/app/my/list/page/vo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getAppQuestionAndOption GET /api/app/ques/opt/get */
export async function getAppQuestionAndOptionUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppQuestionAndOptionUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseListAppQuestionAndOptionVO_>('/api/app/ques/opt/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** addAndUpdateAppQuestionAndOption POST /api/app/ques/opt/update */
export async function addAndUpdateAppQuestionAndOptionUsingPost(
  body: API.AppQuestionOptionUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/ques/opt/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** doAppReview POST /api/app/review */
export async function doAppReviewUsingPost(body: API.ReviewDTO, options?: { [key: string]: any }) {
  return request<API.BaseResponseBoolean_>('/api/app/review', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** updateApp POST /api/app/update */
export async function updateAppUsingPost(
  body: API.AppUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/app/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
