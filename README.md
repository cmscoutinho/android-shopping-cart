# Shopping Cart Android App

Welcome to the Shopping Cart Android App repository! This project is a simple Android application designed to manage a shopping list. The main objective of this app is to provide a user-friendly interface for adding, viewing, and managing products in a shopping list.

## Features

- **Add Product**: Users can add products with details such as name, price, and quantity.
- **View Products**: A list of added products is displayed, showing their details.
- **Edit Product**: Users can update the details of an existing product.
- **Delete Product**: Users can remove a product from the shopping list.

## Getting Started

### Prerequisites

- Android Studio
- An Android device or emulator running Android API level 21 or higher
- Basic knowledge of Android development and Java

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/shopping-cart-android-app.git
    ```
2. **Open the project**:
    - Launch Android Studio.
    - Select `Open an existing Android Studio project`.
    - Navigate to the cloned repository and select it.

3. **Build the project**:
    - Let Android Studio download the necessary dependencies and build the project.

4. **Run the app**:
    - Connect an Android device or start an emulator.
    - Click on the `Run` button in Android Studio to install and start the app on the device/emulator.

## Project Structure

The project follows the typical Android project structure with some specific components:

- `app/src/main/java/com/yourusername/shoppingcart/`:
    - `activities/`: Contains all the activities, including `MainActivity` and `AddProductActivity`.
    - `models/`: Contains the `Product` class, representing the product entity.
    - `adapters/`: Contains the adapter class for managing the product list display.
    - `database/`: Contains classes for managing the local database operations using `Room`.

- `app/src/main/res/layout/`: Contains all the XML layouts for the activities and list items.

## Usage

### Adding a Product

1. Click on the `Add Product` button in the main activity.
2. Enter the product name, price, and quantity.
3. Click `Save` to add the product to the list.

### Viewing Products

- The main activity displays a list of added products with their details.

### Editing a Product

1. Click on a product in the list to open the edit screen.
2. Update the product details and click `Save`.

### Deleting a Product

1. Swipe left or right on a product in the list to delete it.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

### Steps to Contribute

1. **Fork the repository**.
2. **Create a new branch**:
    ```bash
    git checkout -b feature/your-feature-name
    ```
3. **Make your changes**.
4. **Commit your changes**:
    ```bash
    git commit -m "Add your commit message"
    ```
5. **Push to the branch**:
    ```bash
    git push origin feature/your-feature-name
    ```
6. **Create a pull request**.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Contact

If you have any questions or need further assistance, please contact:

- Claudio Coutinho - [ccoutinho.ti@gmail.com](mailto:ccoutinho.ti@gmail.com)

Thank you for using the Shopping Cart Android App!
