package ru.sbt.qa.day.rerunner.extension;

/**
 * Created by sbt-sokovets-av
 */
class RepeatedIfException extends RuntimeException {

    RepeatedIfException(String message) {
        super(message);
    }
}
