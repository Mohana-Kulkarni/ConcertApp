package com.example.concertticketingapp.model;

import java.util.Map;

public class VerificationResult {

    private String result;
    private String issuerDid;
    private Map<String, String> policy;

    public VerificationResult() {
    }

    public VerificationResult(String result, Map<String, String> policy) {
        this.result = result;
        this.policy = policy;
    }

    @Override
    public String toString() {
        return "VerificationResult{" +
                "result='" + result + '\'' +
                ", policy=" + policy +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, String> getPolicy() {
        return policy;
    }

    public void setPolicy(Map<String, String> policy) {
        this.policy = policy;
    }
    public String getIssuerDid() {
        return issuerDid;
    }
    public void setIssuerDid(String issuerDid) {
        this.issuerDid = issuerDid;
    }

}
