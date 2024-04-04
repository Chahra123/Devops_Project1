# Utiliser une image de base contenant Node.js et Angular CLI
FROM node:14

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers du projet Angular dans le conteneur
COPY . .

# Installer les dépendances du projet
RUN npm install

# Compiler le projet Angular
RUN npm run build --prod
