FROM ubuntu:20.04

# 将本地的Poster-0.0.1-SNAPSHOT.jar复制到Docker镜像的工作目录
COPY Poster-0.0.1-SNAPSHOT.jar /app/Poster-0.0.1-SNAPSHOT.jar

# 更新包管理器，并安装OpenJDK 17
RUN apt update && \
    apt install -y openjdk-17-jre

# 设置工作目录
WORKDIR /app

# 运行JAR文件
CMD ["java", "-jar", "Poster-0.0.1-SNAPSHOT.jar"]
