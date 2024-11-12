# 1. Usa una imagen base de Node.js
FROM node:latest

# 2. Define el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# 3. Copia los archivos de dependencias (package.json y package-lock.json si existe)
COPY package*.json ./

# 4. Instala las dependencias
RUN npm install

# 5. Copia el resto de los archivos de la aplicación
COPY . .

# 6. Expone el puerto que usa la aplicación (por ejemplo, 3000)
EXPOSE 3000

# 7. Comando por defecto para ejecutar la aplicación
CMD ["node", "index.js"]
