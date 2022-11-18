# build 1
FROM node:latest as builder
WORKDIR /app
COPY . .
COPY ../saft-backend/target/openapi.json openapi

RUN npm install -g @ionic/cli@6.20.1 && npm install -g @angular/cli@14.1.0
RUN npm i && npm run ng-openapi-gen:pipeline
RUN ionic build --prod --minifyjs --minifycss --optimizejs

# Deploy into nginx
FROM nginx:alpine
COPY --from=builder app/www /usr/share/nginx/html