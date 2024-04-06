FROM node:alpine
WORKDIR /app
COPY package*.json ./
RUN npm install -g npm@10.5.1
RUN npm install -g @angular/cli
COPY . .
CMD ["npm","run","start"]
