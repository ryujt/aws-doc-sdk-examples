//snippet-sourcedescription:[SubscribeLambda.java demonstrates how to send a confirmation message to a lambda function.]
//snippet-keyword:[Java]
//snippet-sourcesyntax:[java]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Simple Notification Service]
//snippet-service:[sns]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[2019-07-20]
//snippet-sourceauthor:[scmacdon AWS]
/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
//snippet-start:[sns.java2.SubscribeLambda.complete]

package com.example.sns;

//snippet-start:[sns.java2.SubscribeLambda.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
//snippet-end:[sns.java2.SubscribeLambda.import]

public class SubscribeLambda {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "SubscribeLambda - subscribe an Amazon Lambda Function.\n" +
                "Usage: SubscribeLambda <topicArn> <lambdaArn>\n\n" +
                "Where:\n" +
                "  topicArn - the arn of the topic to subscribe.\n\n" +
                "  lambdaArn - the ARN of an AWS Lambda function.\n\n";

       if (args.length < 2) {
           System.out.println(USAGE);
           System.exit(1);
        }

        //snippet-start:[sns.java2.SubscribeLambda.main]
        String topicArn = args[0];
        String lambdaArn = args[1];

        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_WEST_2)
                .build();

        try {

            SubscribeRequest request = SubscribeRequest.builder()
                .protocol("lambda")
                .endpoint(lambdaArn)
                .returnSubscriptionArn(true)
                .topicArn(topicArn)
                .build();

            SubscribeResponse result = snsClient.subscribe(request);

            System.out.println("Subscription ARN: " + result.subscriptionArn() + "\n\n Status was " + result.sdkHttpResponse().statusCode());


         } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        System.exit(1);
    }
     //snippet-end:[sns.java2.SubscribeLambda.main]
    }
}
//snippet-end:[sns.java2.SubscribeLambda.complete]
