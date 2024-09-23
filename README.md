# City Taxi Application

A Spring Boot-based taxi booking system where passengers can book, start, complete, and cancel trips. The system allows drivers to accept trips, and passengers are notified via SMS when the trip status changes. Twilio is used to send SMS notifications.

## Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
  - [Twilio Setup](#twilio-setup)
  - [Database Configuration](#database-configuration)
- [How to Run the Project](#how-to-run-the-project)
- [Key Features](#key-features)
  - [Booking a Trip](#booking-a-trip)
  - [Starting a Trip](#starting-a-trip)
  - [Completing a Trip](#completing-a-trip)
  - [Canceling a Trip](#canceling-a-trip)
- [API Endpoints](#api-endpoints)
- [Contact](#contact)

## Project Overview

This project simulates a taxi booking system where passengers can:
- Book a trip.
- Start the trip when the driver is selected.
- Complete the trip and calculate the fare.
- Cancel the trip before the driver has started it.

The application uses Twilio to send SMS notifications to both passengers and drivers during the key events of the trip.

## Technologies Used

- Java 22
- Spring Boot 3.3.3
- PostgreSQL
- Spring Data JPA
- Twilio API for SMS notifications
- Hibernate for ORM
- MapStruct for DTO mapping
- Lombok for boilerplate code reduction

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 22 or later installed on your machine.
- Maven installed to manage dependencies.
- PostgreSQL installed for the database.
- Twilio account with an active phone number for SMS notifications.

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/city-taxi.git
