FROM node:20-alpine AS frontend
WORKDIR /app/client
COPY client/package*.json ./
RUN npm install
COPY client/ .
RUN npm run build

FROM node:20-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
COPY --from=frontend /app/client/dist ./public
EXPOSE 3000
CMD ["node", "app.js"]
