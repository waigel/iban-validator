# Download npm packages

FROM node:16.13.2-alpine as deps
WORKDIR /app

COPY package.json package-lock.json* ./
RUN \
  if [ -f yarn.lock ]; then yarn --frozen-lockfile; \
  elif [ -f package-lock.json ]; then npm ci; \
  elif [ -f pnpm-lock.yaml ]; then yarn global add pnpm && pnpm i --frozen-lockfile; \
  else echo "Lockfile not found." && exit 1; \
  fi

#
# Builder image to build production ready react page
# with tolgee i18n download.
#

FROM node:16.13.2-alpine as builder
WORKDIR /app

ARG TOLGEE_DOWNLOAD_API_KEY
ENV TOLGEE_DOWNLOAD_API_KEY=${TOLGEE_DOWNLOAD_API_KEY}

ARG REACT_APP_API_ENDPOINT
ENV REACT_APP_API_ENDPOINT=${REACT_APP_API_ENDPOINT}

RUN apk add --no-cache curl 

COPY --from=deps /app/node_modules ./node_modules
COPY . ./

# Install i18n from tolgee
RUN TOLGEE_DOWNLOAD_API_KEY=${TOLGEE_DOWNLOAD_API_KEY} ./scripts/i18n.sh
RUN ls src/i18n/
RUN npm run build


#
# Runner docker image with nginx 
#

FROM nginx:1.21.5-alpine as runner
MAINTAINER "Johannes Waigel"

ENV NODE_ENV production

ARG GIT_BRANCH
ENV GIT_BRANCH=${GIT_BRANCH}
LABEL BRANCH=${GIT_BRANCH}

ARG GIT_COMMIT
ENV GIT_COMMIT=${GIT_COMMIT}
LABEL COMMIT=${GIT_COMMIT}

COPY --from=builder /app/build /usr/share/nginx/html/

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]