FROM alpine:3.11

COPY docs /docs
COPY entry.sh /
RUN chmod +x /entry.sh

CMD [ "/entry.sh" ]
