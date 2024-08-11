// @ts-ignore
/* eslint-disable */
import request from '@/utils/request';

/** execute POST /api/codesandbox/execute */
export async function executeUsingPost(
  body: API.CodeExecuteRequest,
  options?: { [key: string]: any },
) {
  return request<API.BaseResponseCodeExecuteResponse_>('/api/codesandbox/execute', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** getCodeSandboxLanguage GET /api/codesandbox/language */
export async function getCodeSandboxLanguageUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListCodeSandboxLanguage_>('/api/codesandbox/language', {
    method: 'GET',
    ...(options || {}),
  });
}
