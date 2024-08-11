# 使用官方 Ubuntu 20.04 镜像作为基础
FROM ubuntu:20.04

# 更改默认 shell 为 bash
SHELL ["/bin/bash", "-c"]

# 设置时区为上海时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 安装必要的软件包
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        apt-utils \
        software-properties-common \
        zip \
        unzip \
        curl \
        wget \
        tar \
        maven \
        openjdk-8-jdk \
        gcc \
        g++ \
        golang \
        python \
        python3-pip \
        nodejs && \
    apt-get clean

# go
ENV GO111MODULE=on
ENV GOPROXY=https://goproxy.cn,direct
ENV GOCACHE /app
ENV GOTMPDIR /app
# npm
RUN npm config set registry https://registry.npmmirror.com/
# pip
RUN pip config set global.index-url https://pypi.tuna.tsinghua.edu.cn/simple

# 设置工作目录，并将本地代码复制到容器中
WORKDIR /app
COPY pom.xml .
COPY src ./src

# 构建 Maven 项目
RUN mvn package -DskipTests

# 容器启动时运行的命令
CMD ["java", "-jar", "/app/target/qianbot-backend-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]