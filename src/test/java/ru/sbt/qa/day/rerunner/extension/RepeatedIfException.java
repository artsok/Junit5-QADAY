package ru.sbt.qa.day.rerunner.extension;

/**
 * Created by Sokovets-AV
 */
class RepeatedIfException extends RuntimeException {

    RepeatedIfException(String message) {
        super(message);
    }
}
