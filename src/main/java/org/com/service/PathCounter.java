package org.com.service;

import org.com.model.Question;

public interface PathCounter {
    /**
     * Count all branches would be
     * @return count of branches
     */
    int getCountBranches();

    /**
     * Check does current question has counter question
     * @return true if has counter question, false otherwise
     */
    boolean hasNext();

    /**
     * Check does object which method next() return equals not null
     * @param answer user answer
     * @return false when next() return null, true otherwise
     */
    boolean hasNext(String answer);

    /**
     * Return next question in dependence from answer
     * @param answer user answer
     * @return next question if it exists, null otherwise
     */
    Question next(String answer);

    /**
     * Get question in current object
     * @return question
     */
    String getQuestion();

    /**
     * Get first answer in current object
     * @return first answer
     */
    String getFirstAnswer();

    /**
     * Get second answer in current object
     * @return second answer
     */
    String getSecondAnswer();

    /**
     * Return a PathCounter to original state
     */
    void initCounterPaths();
}
