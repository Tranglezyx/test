package com.test.dto;

import com.test.annotation.Column;
import com.test.annotation.GeneratedValue;
import com.test.annotation.Id;
import com.test.annotation.Table;

import java.util.Date;

/**
 * Created by w-longteng on 2017/6/26.
 */
public class dto {

    @Id
//    @Column
//    @GeneratedValue
    private long id;
    @Column("business_flag")
    private String business_flag;
    @Column("business_partner")
    private String business_partner;
    @Column("contract_account")
    private String contract_account;
    @Column("amount")
    private float amount;
    @Column("currency_code")
    private String currency_code;
    @Column("company_code")
    private String company_code;
    @Column("bill_date")
    private String bill_date;
    @Column("bill_time")
    private String bill_time;
    @Column("profit_center")
    private String profit_center;
    @Column("tax_calculation")
    private String tax_calculation;
    @Column("production")
    private String production;
    @Column("sales_org")
    private String sales_org;
    @Column("department")
    private String department;
    @Column("business_type")
    private String business_type;
    @Column("distributton_channel")
    private String distributton_channel;
    @Column("production_group")
    private String production_group;
    @Column("sales_office")
    private String sales_office;
    @Column("key1")
    private String key1;
    @Column("key2")
    private String key2;
    @Column("key3")
    private String key3;
    @Column("key4")
    private String key4;
//    @Column("object_version_number")
    private long object_version_number;
    @Column("creation_date")
    private String creation_date;
//    @Column("profit_center")
    private int created_by;
//    @Column("profit_center")
    private int last_updated_by;
    @Column("last_update_date")
    private String last_update_date;
//    @Column("profit_center")
    private int last_update_login;

    public dto() {
    }

    public dto(String business_flag, String business_partner, String contract_account, float amount, String currency_code, String company_code, String bill_date, String bill_time, String profit_center, String tax_calculation, String production, String sales_org, String department, String business_type, String distributton_channel, String production_group, String sales_office, String key1, String key2, String key3, String key4, long object_version_number, String creation_date, int created_by, int last_updated_by, String last_update_date, int last_update_login) {
        this.business_flag = business_flag;
        this.business_partner = business_partner;
        this.contract_account = contract_account;
        this.amount = amount;
        this.currency_code = currency_code;
        this.company_code = company_code;
        this.bill_date = bill_date;
        this.bill_time = bill_time;
        this.profit_center = profit_center;
        this.tax_calculation = tax_calculation;
        this.production = production;
        this.sales_org = sales_org;
        this.department = department;
        this.business_type = business_type;
        this.distributton_channel = distributton_channel;
        this.production_group = production_group;
        this.sales_office = sales_office;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
        this.object_version_number = object_version_number;
        this.creation_date = creation_date;
        this.created_by = created_by;
        this.last_updated_by = last_updated_by;
        this.last_update_date = last_update_date;
        this.last_update_login = last_update_login;
    }

    public dto(long id, String business_flag, String business_partner, String contract_account, float amount, String currency_code, String company_code, String bill_date, String bill_time, String profit_center, String tax_calculation, String production, String sales_org, String department, String business_type, String distributton_channel, String production_group, String sales_office, String key1, String key2, String key3, String key4, long object_version_number, String creation_date, int created_by, int last_updated_by, String last_update_date, int last_update_login) {
        this.id = id;
        this.business_flag = business_flag;
        this.business_partner = business_partner;
        this.contract_account = contract_account;
        this.amount = amount;
        this.currency_code = currency_code;
        this.company_code = company_code;
        this.bill_date = bill_date;
        this.bill_time = bill_time;
        this.profit_center = profit_center;
        this.tax_calculation = tax_calculation;
        this.production = production;
        this.sales_org = sales_org;
        this.department = department;
        this.business_type = business_type;
        this.distributton_channel = distributton_channel;
        this.production_group = production_group;
        this.sales_office = sales_office;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
        this.key4 = key4;
        this.object_version_number = object_version_number;
        this.creation_date = creation_date;
        this.created_by = created_by;
        this.last_updated_by = last_updated_by;
        this.last_update_date = last_update_date;
        this.last_update_login = last_update_login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBusiness_flag() {
        return business_flag;
    }

    public void setBusiness_flag(String business_flag) {
        this.business_flag = business_flag;
    }

    public String getBusiness_partner() {
        return business_partner;
    }

    public void setBusiness_partner(String business_partner) {
        this.business_partner = business_partner;
    }

    public String getContract_account() {
        return contract_account;
    }

    public void setContract_account(String contract_account) {
        this.contract_account = contract_account;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_time() {
        return bill_time;
    }

    public void setBill_time(String bill_time) {
        this.bill_time = bill_time;
    }

    public String getProfit_center() {
        return profit_center;
    }

    public void setProfit_center(String profit_center) {
        this.profit_center = profit_center;
    }

    public String getTax_calculation() {
        return tax_calculation;
    }

    public void setTax_calculation(String tax_calculation) {
        this.tax_calculation = tax_calculation;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getSales_org() {
        return sales_org;
    }

    public void setSales_org(String sales_org) {
        this.sales_org = sales_org;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getDistributton_channel() {
        return distributton_channel;
    }

    public void setDistributton_channel(String distributton_channel) {
        this.distributton_channel = distributton_channel;
    }

    public String getProduction_group() {
        return production_group;
    }

    public void setProduction_group(String production_group) {
        this.production_group = production_group;
    }

    public String getSales_office() {
        return sales_office;
    }

    public void setSales_office(String sales_office) {
        this.sales_office = sales_office;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getKey4() {
        return key4;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public long getObject_version_number() {
        return object_version_number;
    }

    public void setObject_version_number(long object_version_number) {
        this.object_version_number = object_version_number;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public int getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(int last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public String getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(String last_update_date) {
        this.last_update_date = last_update_date;
    }

    public int getLast_update_login() {
        return last_update_login;
    }

    public void setLast_update_login(int last_update_login) {
        this.last_update_login = last_update_login;
    }
}
