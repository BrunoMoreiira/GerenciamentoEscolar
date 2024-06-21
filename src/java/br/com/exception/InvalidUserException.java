/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exception;

import jakarta.servlet.ServletException;

/**
 *
 * @author Contabilidade
 */
public class InvalidUserException extends ServletException {

    public InvalidUserException(String message) {
        super(message);
    }

}
