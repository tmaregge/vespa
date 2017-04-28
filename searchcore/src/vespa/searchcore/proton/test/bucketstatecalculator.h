// Copyright 2016 Yahoo Inc. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
#pragma once

#include <vespa/document/bucket/bucketid.h>
#include <vespa/searchcore/proton/server/ibucketstatecalculator.h>

namespace proton {

namespace test {

typedef document::BucketId::List BucketIdVector;
typedef std::set<document::BucketId>    BucketIdSet;

class BucketStateCalculator : public IBucketStateCalculator
{
private:
    BucketIdSet            _ready;
    mutable BucketIdVector _asked;
    bool                   _clusterUp;
    bool                   _nodeUp;
    bool                   _nodeRetired;

public:
    typedef std::shared_ptr<BucketStateCalculator> SP;
    BucketStateCalculator() :
        _ready(),
        _asked(),
        _clusterUp(true),
        _nodeUp(true),
        _nodeRetired(false)
    {
    }
    BucketStateCalculator &addReady(const document::BucketId &bucket) {
        _ready.insert(bucket);
        return *this;
    }
    BucketStateCalculator &remReady(const document::BucketId &bucket) {
        _ready.erase(bucket);
        return *this;
    }
    BucketStateCalculator &setClusterUp(bool value) noexcept {
        _clusterUp = value;
        return *this;
    }

    BucketStateCalculator & setNodeUp(bool value) noexcept {
        _nodeUp = value;
        return *this;
    }

    BucketStateCalculator& setNodeRetired(bool retired) noexcept {
        _nodeRetired = retired;
        return *this;
    }

    const BucketIdVector &asked() const noexcept { return _asked; }
    void resetAsked() { _asked.clear(); }

    // Implements IBucketStateCalculator
    bool shouldBeReady(const document::BucketId &bucket) const override {
        _asked.push_back(bucket);
        return _ready.count(bucket) == 1;
    }

    bool clusterUp() const override {
        return _clusterUp;
    }
    
    bool nodeUp() const override {
        return _nodeUp;
    }

    bool nodeRetired() const override {
        return _nodeRetired;
    }

    bool nodeInitializing() const override {
        return false;
    }
};


} // namespace test

} // namespace proton

