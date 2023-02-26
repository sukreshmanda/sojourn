FROM node:18.12.1-alpine as build
WORKDIR /sojourn
COPY . .
WORKDIR /sojourn/sojourn-ui
RUN npm install
RUN npm run build
RUN ls build

FROM nginx:stable-alpine as run
WORKDIR /app
COPY --from=build /sojourn/sojourn-ui/build /usr/share/nginx/html/
RUN ls /usr/share/nginx/html/