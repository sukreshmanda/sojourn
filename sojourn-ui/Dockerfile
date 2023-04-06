FROM node:18.12.1-alpine as build
WORKDIR /sojourn-ui
COPY . .
RUN npm install
RUN npm run build

FROM nginx:stable-alpine as run
WORKDIR /app
COPY --from=build /sojourn-ui/build /usr/share/nginx/html/
RUN ls /usr/share/nginx/html/