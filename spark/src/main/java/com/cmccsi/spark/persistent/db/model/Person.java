package com.cmccsi.spark.persistent.db.model;

public class Person {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person.name
     *
     * @mbggenerated Tue Jun 18 16:49:05 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column person.count
     *
     * @mbggenerated Tue Jun 18 16:49:05 CST 2019
     */
    private Integer count;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person.name
     *
     * @return the value of person.name
     *
     * @mbggenerated Tue Jun 18 16:49:05 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person.name
     *
     * @param name the value for person.name
     *
     * @mbggenerated Tue Jun 18 16:49:05 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column person.count
     *
     * @return the value of person.count
     *
     * @mbggenerated Tue Jun 18 16:49:05 CST 2019
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column person.count
     *
     * @param count the value for person.count
     *
     * @mbggenerated Tue Jun 18 16:49:05 CST 2019
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}