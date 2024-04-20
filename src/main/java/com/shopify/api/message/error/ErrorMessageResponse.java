package com.shopify.api.message.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessageResponse
{
    String Code;
    String Message;
}
