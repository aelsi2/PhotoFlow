# PhotoFlow Frontend

A frontend for PhotoFlow project written in React.

## Tech Stack

- **Core**: React 19, TypeScript, Vite
- **Routing**: TanStack Router
- **Server State**: TanStack Query
- **UI & Styling**: Mantine
- **Code Quality**: ESLint, Prettier
- **Packge Manager**: pnpm

## Prerequisites

Make sure you have the following installed on your machine:

- [Node.js](https://nodejs.org) (v22 or v24 and higher)
- [pnpm](https://pnpm.io) (v10 or higher)

## Getting Started

1. **Clone repo**
2. **Install dependencies**
   ```bash
   pnpm install
   ```
3. **Run development server**

   ```bash
   pnpm dev
   ```

   The application will be available at `http://localhost:5173/`

   > **Tip:** If the backend is unavailable, you can run the local mock server in a separate terminal:
   >
   > ```bash
   > pnpm api:mock
   > ```

## Available Scripts

| Script            | Description                                            |
| :---------------- | :----------------------------------------------------- |
| pnpm dev          | Starts the Vite development server                     |
| pnpm build        | Type-checks and builds the app for production          |
| pnpm preview      | Locally previews the production build                  |
| pnpm lint         | Runs ESLint across the project                         |
| pnpm lint:fix     | Applies ESLint auto-fixes                              |
| pnpm format       | Formats code using Prettier                            |
| pnpm format:check | Checks format using Prettier                           |
| pnpm api:gen      | Generates API models and query hooks from OpenAPI spec |
| pnpm api:mock     | Starts the local mock API server                       |
