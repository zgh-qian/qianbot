// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** addChart POST /api/chart/add/data */
export async function addChartUsingPost(
  body: API.ChartAddRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseLong_>('/api/chart/add/data', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** addChartByFile POST /api/chart/add/file */
export async function addChartByFileUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.addChartByFileUsingPOSTParams,
  body: {},
  file?: File,
  options?: { [key: string]: any },
) {
  const formData = new FormData();

  if (file) {
    formData.append('file', file);
  }

  Object.keys(body).forEach((ele) => {
    const item = (body as any)[ele];

    if (item !== undefined && item !== null) {
      if (typeof item === 'object' && !(item instanceof File)) {
        if (item instanceof Array) {
          item.forEach((f) => formData.append(ele, f || ''));
        } else {
          formData.append(ele, JSON.stringify(item));
        }
      } else {
        formData.append(ele, item);
      }
    }
  });

  return request<API.BaseResponseLong_>('/api/chart/add/file', {
    method: 'POST',
    params: {
      ...params,
    },
    data: formData,
    // @ts-ignore
    requestType: 'form',
    ...(options || {}),
  });
}

/** genChart GET /api/chart/ai/gen */
export async function genChartUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.genChartUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/chart/ai/gen', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteChart POST /api/chart/delete */
export async function deleteChartUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/chart/delete', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getChartVOById GET /api/chart/get/vo */
export async function getChartVoByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getChartVOByIdUsingGETParams,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseChartVO_>('/api/chart/get/vo', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getChartVOPage POST /api/chart/get/vo/page */
export async function getChartVoPageUsingPost(
  body: API.ChartQueryRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponsePageChartVO_>('/api/chart/get/vo/page', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** updateChart POST /api/chart/update */
export async function updateChartUsingPost(
  body: API.ChartUpdateRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseBoolean_>('/api/chart/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
