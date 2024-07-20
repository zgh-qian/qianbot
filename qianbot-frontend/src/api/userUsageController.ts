// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** getUserRemainingCount POST /api/user/usage/query */
export async function getUserRemainingCountUsingPost(
  body: API.UserUsageDTO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseInt_>('/api/user/usage/query', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getDefaultRemainingCount POST /api/user/usage/query/default */
export async function getDefaultRemainingCountUsingPost(
  body: API.UserUsageDTO,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseInt_>('/api/user/usage/query/default', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
