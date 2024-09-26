# City Taxi Application

A Spring Boot-based taxi booking system where passengers can book, start, complete, and cancel trips. The system allows drivers to accept trips, and passengers are notified via SMS when the trip status changes. Twilio is used to send SMS notifications.
 
## Table of Contents

- Project Overview
- Technologies Used
- Prerequisites
- Installation
- Configuration
  - Twilio Setup
  - Database Configuration
- How to Run the Project
- Key Features
  - Booking a Trip
  - Starting a Trip
  - Completing a Trip
  - Canceling a Trip
- API Endpoints
- Contact

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

1. Clone the repository:
   
   git clone https://github.com/your-username/city-taxi.git

2. Navigate to the project directory:

   cd city-taxi

3. Install dependencies:

   mvn clean install

## Configuration

### Twilio Setup

To send SMS notifications using Twilio, you'll need to sign up for a Twilio account and obtain your **Account SID**, **Auth Token**, and **Twilio Phone Number**.

Add the following configurations to your `application.properties`:

