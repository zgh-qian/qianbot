const { generateService } = require("@umijs/openapi");

generateService({
  requestLibPath: `import request from "@/utils/request"`,
  schemaPath: "http://localhost:9000/api/v2/api-docs",
  serversPath: "./src",
});
