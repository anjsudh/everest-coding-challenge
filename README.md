# Courier Delivery Management System

This project is a modular Courier Delivery Management System written in Java. It is designed to handle the pricing and estimated time of arrival (ETA) calculations for packages based on various criteria such as package weight, distance, and available offers. The system uses a knapsack algorithm to optimize package assignments to vehicles.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Setup](#setup)
- [Usage](#usage)

## Overview

The Courier Delivery Management System is built to calculate the cost and estimated delivery time for packages in a delivery service. It uses various strategies to apply discounts and optimize delivery schedules using vehicles with weight and speed constraints. The system includes models for packages, deliveries, and vehicles, and it supports dynamic pricing based on offers.

## Features

- **Delivery Cost Estimation with Offers**
- **Delivery Time Estimation**

## Setup

### Prerequisites

- **JDK 22**: Make sure you have JDK 22 installed. You can download it from the official [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk22-downloads.html) website.
- **Gradle**: Install Gradle for dependency management and building the project. You can follow the installation guide on the official [Gradle website](https://gradle.org/install/).

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/anjsudh/everest-coding-challenge.git
   cd everest-coding-challenge
   ```
   
2. Build the project using Gradle:
   ```bash
    gradle build
   ```
 
3. Run the application:
   ```bash
   gradle run
   ```
 
### Usage
To make changes to the input, open the input.txt file and edit it accordingly.

#### Input Format
```
base_delivery_cost no_of_packges
pkg_id1 pkg_weight1_in_kg distance1_in_km offer_code1

....
no_of_vehicles max_speed max_carriable_weight
```
##### Example Input
```bash
100 5
PKG1 50 30 OFR001
PKG2 75 125 OFFR0008
PKG3 175 100 OFFR003
PKG4 110 60 OFFR002
PKG5 155 95 NA
2 70 200
```

#### Output Format
```
pkg_id1 discount1 total_cost1 estimated_delivery_time1_in_hours

...
```
##### Example Output
```bash
PKG1 0 750 3.98
PKG2 0 1475 1.78
PKG3 0 2350 1.42
PKG4 105 1395 0.85
PKG5 0 2125 4.19
```
