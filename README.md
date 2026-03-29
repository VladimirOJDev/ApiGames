# ApiGames - Android App
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-Studio-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack-Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

**ApiGames** es una solución móvil nativa diseñada para la exploración exhaustiva de videojuegos. La aplicación no solo consume datos en tiempo real de la RAWG API, sino que implementa estándares modernos de ingeniería de software para garantizar una experiencia de usuario fluida y adaptable.

## ✨ Características Principales


## 🏗️ Arquitectura y Estructura del Proyecto

El proyecto se rige bajo los principios de **Clean Architecture** y **SOLID**, estructurado en tres capas independientes:

### 1. Data Layer
* **Consumo de API:** Implementación de **Retrofit** para comunicación con RAWG.
* **Mappers:** Transformación de *Data Transfer Objects (DTOs)* a *Domain Models*, evitando que la capa de negocio dependa de la estructura de la API.
* **Resource Wrapper:** Gestión centralizada de respuestas (Success, Error, Loading).

### 2. Domain Layer
* Contiene la lógica de negocio pura y las entidades.
* **Use Cases:** Orquestación de la lógica necesaria para cada pantalla, manteniendo los ViewModels limpios y enfocados.

### 3. UI Layer (Presentation)
* **Jetpack Compose:** Interfaz 100% declarativa y moderna.
* **Pattern MVVM + UDF:** Flujo unidireccional de datos para una interfaz predecible y fácil de depurar.
* **StateFlow:** Uso de `collectAsStateWithLifecycle` para un consumo de recursos eficiente según el ciclo de vida de Android.


## 📱 Adaptive UI (Plegables y Tablets)
Uno de los mayores retos fue implementar una interfaz **responsive**. Utilizando WindowSizeClass, la app decide dinámicamente cómo mostrar el contenido:

* **Compact (Móviles):** Navegación estándar de lista a detalle.

* **Expanded (Tablets/Plegables):**  Layout tipo Master-Detail (Lista y detalle en la misma pantalla), optimizando el espacio disponible. Implementado mediante un sistema de estados GameContentType (LIST_ONLY / LIST_AND_DETAIL).

## 🛠️ Tecnologías y Librerías
* **Inyección de Dependencias:** Hilt + Hilt Navigation Compose.

* **Networking:** Retrofit & Gson para el consumo de la API de Rawg.

* **Carga de Imágenes:** Coil (optimizado para Compose).

* **Navegación:** Navigation Compose con Kotlin Serialization para tipos de datos seguros.

* **Gestión de Estados:**  StateFlow & collectAsStateWithLifecycle.

## ⚙️ Instalación y Configuración
Para ejecutar este proyecto localmente:

1. Clona el repositorio.
2. Obtén tu API Key en [RAWG.io](https://rawg.io/apidocs).
3. Configura tu API Key en el proyecto.
4. `Build & Run`.
