
# Wearable Devices Store System

This is a menu-driven console application that allows users to manage a collection of wearable devices, specifically Smart Bands and Smart Watches. The information entered via the app is persisted in XML files.

# Table of Contents

Features

Classes Overview

Usage
Installation

Code Structure

Credits

# Features
Add, update, delete, and list wearable devices.

Search devices by various criteria such as ID, manufacturer, and price range.

Sort devices by price in ascending or descending order.

Generate reports on the wearable devices.

Save and load data to and from XML files.
# Classes Overview

# Controllers

WearableDeviceAPI

Manages a list of WearableDevice objects.

Provides CRUD operations, reporting methods, sorting methods, and persistence methods.

# Models

## WearableDevice

Abstract class representing a wearable device.

Contains fields like size, price, manufacturerName, material, modelName, and id.

Abstract methods getInsurancePremium and connectToInternet.

## SmartBand

Extends WearableDevice.

Contains an additional field for heartRateMonitor.

Implements abstract methods.

## SmartWatch

Extends WearableDevice.

Contains an additional field for displayType.

Implements abstract methods.

# Utilities

## DisplayTypeUtility

Provides validation for display types.

## ManufacturerNameUtility

Provides validation for manufacturer names.

## ScannerInput
Provides methods for robust handling of user input.

## Utilities
Contains various utility methods for validation and conversion.

# Usage

1. Run the Application: Execute the main method in the Driver class.
2. Main Menu: Navigate through the main menu to perform different operations like adding, updating, deleting, listing, searching, and sorting wearable devices.
3. CRUD Operations: Add new wearable devices (Smart Band or Smart Watch), update existing ones, or delete them.
4. Search and Sort: Search devices by ID, manufacturer, or price range. Sort devices by price.
5. Reports: Generate various reports on the wearable devices.
6. Save and Load: Save the current state to an XML file or load data from an XML file.

# Installation
1. Clone the Repository: git clone <repository-url>
2. Open the Project: Open the project in your favorite IDE (e.g., IntelliJ IDEA).
3. Run the Application: Execute the main method in the Driver class.


