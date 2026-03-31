# ApiGames - Android App
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-Studio-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack-Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

**ApiGames** es una solución móvil nativa diseñada para la exploración de videojuegos. La aplicación no solo consume datos en tiempo real de la RAWG API, sino que implementa estándares modernos de ingeniería de software para garantizar una experiencia de usuario fluida y adaptable.

## ✨ Características Principales
* **Exploración Dinámica:** Listado exhaustivo de videojuegos con consumo de API en tiempo real.
* **Búsqueda Reactiva:** Implementación de `SearchBar` de Material3 con filtrado en tiempo real.
* **Diseño Responsivo (Adaptive UI):** Layout inteligente que alterna entre navegación estándar (Móvil) y panel dual **Master-Detail** (Tablets/Plegables).
* **Gestión de Estados:** Manejo robusto de estados de carga, error y éxito mediante programación reactiva.


## 🏗️ Arquitectura y Estructura del Proyecto

El proyecto implementa una separación estricta de responsabilidades, se rige bajo los principios de **Clean Architecture** y **SOLID**, estructurado en tres capas independientes:

### 1. Capa de Data (Infraestructura)
* **Retrofit & OKHttp:** Gestión de peticiones de red y configuración de `BASE_URL`.
* **Mappers:** Uso de funciones de extensión (`toDomain()`) para transformar DTOs en entidades puras de negocio, protegiendo la app de cambios en la API.
* **Security:** API Key protegida mediante `BuildConfig` y `local.properties`.
* **Resource Wrapper:** Gestión centralizada de respuestas (Success, Error, Loading).

### 2. Capa de Domain (Lógica de Negocio)
* **Entities:** Modelos de datos puros en Kotlin (`Game`, `GameDetail`).
* **Repository Interface:** Definición de contratos para la inversión de dependencias.
* **Use Cases:** Implementación de casos de uso independientes (`GetGamesUseCase`, `GetGameByIdUseCase`) mediante el operador `invoke`.

### 3. Capa de UI (Presentación)
* **Jetpack Compose:** Interfaz 100% declarativa con animaciones de transición (`Crossfade`).
* **MVVM + UDF:** Flujo unidireccional de datos utilizando `MutableStateFlow` y `collectAsStateWithLifecycle`.
* **State Management:** Uso de `HomeState` para representar de forma atómica el estado de la pantalla.


## 📱 Implementación de Adaptive UI

El sistema detecta dinámicamente el tamaño de la ventana mediante `WindowSizeClass`:

* **Compact/Medium:** La navegación fluye de una pantalla a otra (Navegación lineal).
* **Expanded:** Se activa el `HomeAndDetailView`, que utiliza una estructura de `Row` y `weights` para mostrar la lista y los detalles en paralelo, optimizando el espacio en dispositivos de pantalla grande.

## 🛠️ Tech Stack & Librerías

* **Lenguaje:** Kotlin (Coroutines & Flow).
* **DI:** Hilt (Dependency Injection) con módulos específicos para App y Repositorios.
* **Navegación:** Navigation Compose **Type-Safe** (usando Kotlin Serialization).
* **UI:** Jetpack Compose, Material3, Coil (Imágenes), WindowSizeClass.
* **Arquitectura:** Clean Architecture + MVVM + UDF.

  
---


## ⚙️ Instalación y Configuración

Para ejecutar este proyecto localmente y configurar tu propia llave de API, sigue estos pasos:

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/VladimirOJDev/ApiGames.git
   ```
2. **Configurar la API Key (RAWG API):**
    Este proyecto utiliza BuildConfig para mantener las llaves seguras. Abre el archivo local.properties en la raíz de tu proyecto y añade la siguiente línea:
   ```bash
    API_KEY=tu_api_key_aqui
    ```
3. Cambiar las variables de entorno (The MovieDB)
   Abre el archivo .env y añade tu llave:
    ```bash
    MOVIE_DB_KEY=tu_api_key_aqui
    ```
4. Instalar dependencias y limpiar caché de Gradle:
   ```bash
   ./gradlew clean
    ```
5. Generar código y construir el proyecto
   ```bash
    ./gradlew assembleDebug
    ```
6. Ejecutar la aplicación
   Asegúrate de tener un emulador abierto o un dispositivo conectado:
      ```bash
    ./gradlew installDebug
    ```
      
## 🔐 Configuración de API Key

El proyecto utiliza `BuildConfig` para gestionar las credenciales de forma segura y evitar que se filtren en el control de versiones.

1. **Obtener Credenciales:**
   Regístrate en [RAWG.io](https://rawg.io/apidocs) para obtener tu propia `API_KEY`.

2. **Configurar local.properties:**
   En la raíz del proyecto, abre (o crea) el archivo `local.properties` y añade la siguiente variable:
   ```properties
   API_KEY=tu_api_key_aqui
---
